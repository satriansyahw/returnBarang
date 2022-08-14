package com.example.barang.service;

import com.example.barang.dto.request.ReturnsReqDto;
import com.example.barang.dto.response.ReturnsResDto;
import com.example.barang.persistence.dao.ItemsSkuDao;
import com.example.barang.persistence.dao.OrderTransDao;
import com.example.barang.persistence.dao.ReturnsDao;
import com.example.barang.persistence.dao.ReturnsDetailDao;
import com.example.barang.persistence.domain.ItemsSku;
import com.example.barang.persistence.domain.Returns;
import com.example.barang.persistence.domain.ReturnsDetail;
import com.example.barang.persistence.projection.ReturnsData;
import com.example.barang.util.DataResponse;
import com.example.barang.util.GenericResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class ReturnsServiceTests {
    @InjectMocks
    private ReturnsService returnsService;
    @Mock
    private ReturnsDao returnsDao;

    @Mock
    private OrderTransDao orderTransDao;

    @Mock
    private ItemsSkuDao itemsSkuDao;

    @Mock
    private ReturnsDetailDao returnsDetailDao;


    @Test
    public void test_getReturnsById_WhenId_Not_Exists_Return_dataResponseData_null()
    {
        //given
        Integer id =0;
        //when
        when(returnsDao.getReturnDataById(id)).thenReturn(null);
        //then
        DataResponse result = returnsService.getReturnsById(id);
        DataResponse expected = new DataResponse();
        expected.setData(null);
        Assertions.assertEquals(expected.getData(),result.getData());
    }
    @Test
    public void test_getReturnsById_WhenId_Exists_Return_dataResponseData_Notnull()
    {
        //given
        Integer id =1;
        //when//List<ReturnsData>
        ReturnsData returnsData= new ReturnsData() {
            @Override
            public Integer getReturnId() {
                return null;
            }

            @Override
            public Integer getItemDetailId() {
                return null;
            }

            @Override
            public String getOrderId() {
                return null;
            }

            @Override
            public String getStatus() {
                return null;
            }

            @Override
            public String getSku() {
                return null;
            }

            @Override
            public Integer getQty() {
                return null;
            }

            @Override
            public String getQcStatus() {
                return null;
            }

            @Override
            public double getPrice() {
                return 0;
            }
        };
        when(returnsDao.getReturnDataById(id)).thenReturn(
                List.of(returnsData,returnsData)
        );
        //then
        DataResponse result = returnsService.getReturnsById(id);
        Assertions.assertNotNull(result.getData());
    }
    @Test
    public void test_creatingReturns_WhenValidData_Empty_then_return_ReturnsResDto_Null() {
        //given
        List<ReturnsReqDto>  returnsReqDtos = new ArrayList<>();
        returnsReqDtos.add(new ReturnsReqDto());
        returnsReqDtos.add(new ReturnsReqDto());

        // when
        doReturn(false).when(orderTransDao).isExistsOrderAndSku("orderid","sku");

        DataResponse result =  returnsService.creatingReturns(returnsReqDtos);
        ReturnsResDto result1 =  (ReturnsResDto)result.getData();
        Assertions.assertNull(result1.getReturnsId());
    }
    @Test
    public void test_creatingReturns_WhenValidData_NotEmpty_then_return_ReturnsResDto_NotNull() {
        //given
        List<ReturnsReqDto>  returnsReqDtos = new ArrayList<>();
        ReturnsReqDto returnsReqDto = new ReturnsReqDto();
        returnsReqDto.setOrderId("orderId");
        returnsReqDto.setSku("sku");
        returnsReqDtos.add(returnsReqDto);

        // when
        doReturn(true).when(orderTransDao).isExistsOrderAndSku(any(),any());
        when(itemsSkuDao.getItemSkuBySku(any())).thenReturn(ItemsSku.builder().price(10).build());
        doReturn(Returns.builder()
                .id(1).orderId("any()").build()).when(returnsDao).saveOrUpdate(any());
        doReturn(List.of(ReturnsDetail.builder().id(1).returnsId(1).build()))
                .when(returnsDetailDao).saveBulk(List.of(ReturnsDetail.builder().returnsId(1).build()));
        DataResponse result =  returnsService.creatingReturns(returnsReqDtos);
        ReturnsResDto result1 =  (ReturnsResDto)result.getData();
        Assertions.assertNotNull(result1.getReturnsId());
    }
    @Test
    public void test_updateReturnsByIdAndStatus_WhenReturnId_existAndStatus_Complete_then_returnSuccess() {

        when( returnsDao.getReturnsById(1)).thenReturn(Returns.builder().id(1).orderId("orderId").status("AWAITING_APPROVAL").build());
        when(returnsDao.saveOrUpdate(Returns.builder().orderId("orderId").status("AWAITING_APPROVAL").build()))
                .thenReturn(Returns.builder().id(1).orderId("orderId").status("AWAITING_APPROVAL").build());
        GenericResponse genericResponse=  returnsService.updateReturnsByIdAndStatus(1,"AWAITING_APPROVAL");
        Assertions.assertSame("Returns status successfully updated",genericResponse.getMessage());
    }
    @Test
    public void test_updateReturnsDetailByStatusAndItemDetailId_WhenReturnIdAndDetaildId_NotNull_then_returnSuccess() {
        when( returnsDetailDao.getByIdAndreReturnsId(1,1)).thenReturn(ReturnsDetail.builder().id(1).qcStatus("ACCEPTED").build());
        when( returnsDetailDao.saveOrUpdate(ReturnsDetail.builder().qcStatus("ACCEPTED").build())).thenReturn(ReturnsDetail.builder().id(1).qcStatus("ACCEPTED").build());
        GenericResponse genericResponse=  returnsService.updateReturnsDetailByStatusAndItemDetailId(1,1,"ACCEPTED");
        Assertions.assertSame("Item status successfully updated",genericResponse.getMessage());
    }
}
