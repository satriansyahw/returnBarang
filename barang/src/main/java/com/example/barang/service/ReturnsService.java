package com.example.barang.service;

import com.example.barang.dto.request.ReturnsReqDto;
import com.example.barang.dto.response.ReturnByIdResDto;
import com.example.barang.dto.response.ReturnsDetailResDto;
import com.example.barang.dto.response.ReturnsResDto;
import com.example.barang.enums.QcStatusEnums;
import com.example.barang.enums.ReturnsStatusEnums;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ReturnsService {
    private static final Logger logger = LogManager.getLogger(ReturnsService.class);
    @Autowired
    private ReturnsDao returnsDao;

    @Autowired
    private ItemsSkuDao itemsSkuDao;

    @Autowired
    private OrderTransDao orderTransDao;

    @Autowired
    private ReturnsDetailDao returnsDetailDao;

    public DataResponse creatingReturns(List<ReturnsReqDto> returnsReqDtos){
        logger.info("Starting creating Returns ...");
        List<ReturnsReqDto>  invalidReturns= new ArrayList<>();
        List<ReturnsReqDto>  validReturns= new ArrayList<>();
        ReturnsResDto returnsResDto = new ReturnsResDto();
        returnsReqDtos.forEach((ReturnsReqDto returns) -> {
            boolean isValidReturn= orderTransDao.isExistsOrderAndSku(returns.getOrderId(),returns.getSku());
            if(isValidReturn)
            {
                List<ReturnsData> returnsDataList = this.getReturnByOrderIdAndSku(returns.getOrderId(),returns.getSku());
                if(returnsDataList.isEmpty()) {
                    validReturns.add(returns);
                }
                else {
                    invalidReturns.add(returns);
                }
            }
            else
            {
                invalidReturns.add(returns);
            }
            logger.info("Checking valid Returns : "+validReturns.size()+" data");

        });
        Returns returns=null;
        List<ReturnsDetail> returnsDetailList=new ArrayList<>();
        double totPrice = 0;
        if(!validReturns.isEmpty())
        {
            logger.info("Processing Returns data");
            returns = returnsDao.saveOrUpdate(Returns.builder()
                .orderId(validReturns.get(0).getOrderId())
                .status(ReturnsStatusEnums.AWAITING_APPROVAL.name())
                .build());
            List<ReturnsDetail> detailList = new ArrayList<>();

            Returns finalReturns = returns;
            for (ReturnsReqDto req : validReturns) {
                ItemsSku itemsSku = itemsSkuDao.getItemSkuBySku(req.getSku());

                totPrice = totPrice + (req.getQty() * itemsSku.getPrice());
                detailList.add(ReturnsDetail.builder()
                        .returnsId(finalReturns.getId())
                        .qty(req.getQty())
                        .sku(req.getSku())
                        .price(itemsSku.getPrice())
                        .qcStatus(QcStatusEnums.ACCEPTED.name())
                        .build());
                returnsDetailList =  returnsDetailDao.saveBulk(detailList);

            }
        }
        if(returns!=null){
            logger.info("Creating Returns response data");
            returnsResDto.setReturnsId(returns.getId());
            returnsResDto.setOrderId(returns.getOrderId());
            returnsResDto.setRefundAmount(totPrice);
            if(!returnsDetailList.isEmpty()) {
                List<ReturnsDetailResDto> returnsDetailResDtos = new ArrayList<>();
                ReturnsDetailResDto returnsDetailResDto=null;
                for (ReturnsDetail detail : returnsDetailList) {
                    returnsDetailResDto = new ReturnsDetailResDto();
                    returnsDetailResDto.setQty(detail.getQty());
                    returnsDetailResDto.setSku(detail.getSku());
                    returnsDetailResDto.setPrice(detail.getPrice());
                    returnsDetailResDto.setItemDetailId(detail.getId());
                    returnsDetailResDtos.add(returnsDetailResDto);
                }
                returnsResDto.setReturnsDetail(returnsDetailResDtos);
            }
        }
        returnsResDto.setInvalidReturns(invalidReturns);
        String message =returns!=null ? "Returns processed":" No Returns Data processed";
        return new DataResponse(true, returnsResDto,message);
    }
    public List<ReturnsData> getReturnByOrderIdAndSku(String orderId,String sku){
        return returnsDao.getReturnByOrderIdAndSku(orderId,sku);
    }
    public DataResponse getReturnsById(Integer id)
    {
        logger.info("Processing get returns by id");
        List<ReturnsData> returnsDataList =  returnsDao.getReturnById(id);
        List<ReturnsDetailResDto> returnsDetailResDtos = new ArrayList<>();
        ReturnByIdResDto returnByIdResDto = null;
        if(!returnsDataList.isEmpty()) {
            returnByIdResDto = new ReturnByIdResDto();
            double totPrice = 0;
            ReturnsDetailResDto returnsDetailResDto =null;
            for (ReturnsData returnsData : returnsDataList) {
                if (QcStatusEnums.ACCEPTED.name().equals(returnsData.getQcStatus())) {
                    ItemsSku itemsSku = itemsSkuDao.getItemSkuBySku(returnsData.getSku());
                    totPrice += (returnsData.getQty() * itemsSku.getPrice());
                }
                returnsDetailResDto= new ReturnsDetailResDto();
                returnsDetailResDto.setItemDetailId(returnsData.getItemDetailId());
                returnsDetailResDto.setQcStatus(returnsData.getQcStatus());
                returnsDetailResDto.setPrice(returnsData.getPrice());
                returnsDetailResDto.setQty(returnsData.getQty());
                returnsDetailResDtos.add(returnsDetailResDto);

            }

            returnByIdResDto.setReturnsId(returnsDataList.get(0).getReturnId());
            returnByIdResDto.setRefundAmount(totPrice);
            returnByIdResDto.setDetailItem(returnsDetailResDtos);
        }
        String message = returnByIdResDto !=null ? "Get Returns Data By id " : "No Returns data found ";
        return new DataResponse(true, returnByIdResDto,message);
    }
    public GenericResponse updateReturnsByStatusAndItemDetailId(Integer returnsId,Integer itemDetailId,String status) {

        String message="";
        if(QcStatusEnums.ACCEPTED.name().toLowerCase().equals(status.toLowerCase())
                | QcStatusEnums.REJECTED.name().toLowerCase().equals(status.toLowerCase())) {

            ReturnsDetail checker = returnsDetailDao.getByIdAndreReturnsId(itemDetailId,returnsId);
            if(checker !=null) {
                var ss = returnsDetailDao.saveOrUpdate(ReturnsDetail.builder()
                        .id(itemDetailId)
                        .qcStatus(status.toUpperCase())
                        .returnsId(checker.getReturnsId())
                        .price(checker.getPrice())
                        .sku(checker.getSku())
                        .build());
                logger.info(checker);
                message = "Item status successfully updated";
            }
            else
            {
                message = "Failed, No returns data found ";
            }
        }
        else {
            message = "Failed, status unknown";
        }
        return new GenericResponse(true, message);
    }

}
