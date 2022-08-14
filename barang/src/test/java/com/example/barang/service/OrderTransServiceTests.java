package com.example.barang.service;

import com.example.barang.dto.request.PendingReturnsReqDto;
import com.example.barang.dto.response.ReturnsAuthResDto;
import com.example.barang.persistence.dao.OrderTransDao;
import com.example.barang.persistence.dao.ReturnsAuthDao;
import com.example.barang.persistence.domain.ReturnsAuth;
import com.example.barang.util.DataResponse;
import com.example.barang.util.Jwt.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class OrderTransServiceTests {
    @InjectMocks
    private OrderTransService orderTransService;
    @Mock
    private OrderTransDao orderTransDao;

    @Mock
    private ReturnsAuthDao returnsAuthDao;

    @Mock
    private JwtTokenUtil jwtTokenUtil;


    @Test
    public void test_pendingReturnsAuth_whenAuthorizedFalse_return_dataResponseData_Null()
    {
        //Given
        PendingReturnsReqDto pendingReturnsReqDto = new PendingReturnsReqDto();
        pendingReturnsReqDto.setOrderId("orderId");
        pendingReturnsReqDto.setEmail("email");
        //when
        doReturn(false).when(orderTransDao).isAuthorizedReturnUser("orderId","email");
        DataResponse result =  orderTransService.pendingReturnsAuth(pendingReturnsReqDto);
        DataResponse expected = new DataResponse(true,null,"Failed login");
        Assertions.assertEquals(expected.getData(),result.getData());
    }
    @Test
    public void test_pendingReturnsAuth_whenAuthorizedTrue_return_dataResponseData_NotNull()
    {
        //Given
        ReturnsAuth returnsAuth=ReturnsAuth.builder()
                .email("email").orderId("orderId").token("token")
                .build();
        PendingReturnsReqDto pendingReturnsReqDto = new PendingReturnsReqDto();
        pendingReturnsReqDto.setOrderId("orderId");
        pendingReturnsReqDto.setEmail("email");

        //when
        doReturn(true).when(orderTransDao).isAuthorizedReturnUser("orderId","email");

        when(returnsAuthDao.Save(returnsAuth)).thenReturn(
                ReturnsAuth.builder()
                        .email("email").orderId("orderId").token("token")
                        .id(1)
                        .build()
        );
        when(jwtTokenUtil.generateToken( ReturnsAuth.builder()
                .email("email").orderId("orderId").token("token")
                .id(1)
                .build())).thenReturn("token");
        DataResponse result =  orderTransService.pendingReturnsAuth(pendingReturnsReqDto);
        ReturnsAuthResDto returnsAuthResDto = new ReturnsAuthResDto();
        returnsAuthResDto.setAccessToken("token");
        DataResponse expected = new DataResponse(true,returnsAuthResDto,"Successfully login");
        Assertions.assertNotNull(result.getData());
    }

}
