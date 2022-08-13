package com.example.barang.service;

import com.example.barang.dto.request.PendingReturnsReqDto;
import com.example.barang.dto.response.ReturnsAuthResDto;
import com.example.barang.persistence.dao.OrderTransDao;
import com.example.barang.persistence.dao.ReturnsAuthDao;
import com.example.barang.persistence.domain.ReturnsAuth;
import com.example.barang.util.DataResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderTransService {

    private static Logger logger = LogManager.getLogger(OrderTransService.class);
    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private ReturnsAuthDao returnsAuthDao;

    public DataResponse pendingReturnsAuth(PendingReturnsReqDto returnsReqDto) {
        boolean isAuthUser = orderTransDao.isAuthorizedReturnUser(returnsReqDto.getOrderId(), returnsReqDto.getEmail());
        logger.info("Checking isAuthorizedReturnUser : " + String.valueOf(isAuthUser));
        ReturnsAuthResDto tokenAuth=null;
        if (isAuthUser) {
            String token = this.getToken(returnsReqDto.getOrderId(), returnsReqDto.getEmail());
            ReturnsAuth returnsAuth = ReturnsAuth.builder()
                    .orderId(returnsReqDto.getOrderId())
                    .email(returnsReqDto.getEmail())
                    .token(token)
                    .build();
            returnsAuthDao.Save(returnsAuth);
            tokenAuth = new ReturnsAuthResDto();
            tokenAuth.setAccessToken(token);
            logger.info("Successfully created token");
        }
        String message = tokenAuth !=null?" Successfully login": " Failed login";
        return new DataResponse(true, tokenAuth,message);
    }
    private String getToken(String orderId,String email)
    {
        return "Token__"+orderId+"__"+email;
    }
}
