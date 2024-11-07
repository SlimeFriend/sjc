package com.sjc.app.mt.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjc.app.mt.mapper.StockMapper;
import com.sjc.app.mt.service.MtInVO;
import com.sjc.app.mt.service.MtVO;
import com.sjc.app.mt.service.StockService;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    /**
     * 전체 자재 목록을 가져와 각 자재의 로트별 수량을 합산하여 현재 재고에 반영
     */
    @Override
    public List<MtVO> getAllMaterials() {
        List<MtVO> materials = stockMapper.getAllMaterials(); // 전체 자재 목록 조회

        for (MtVO material : materials) {
            // 각 자재의 로트별 수량 목록 조회
            List<MtInVO> lotDetails = stockMapper.getMaterialsByLotNo(material.getMtCode());
            material.setLotDetails(lotDetails);

//            // 로트별 수량을 합산하여 현재 재고 설정
//            int totalQuantity = lotDetails.stream().mapToInt(MtInVO::getInquantity).sum();
//            material.setCurrentStc(totalQuantity);
        }

        return materials;
    }

    /**
     * 자재 구분 업데이트 기능
     */
    @Override
    public void updateMaterialType(String mtCode, String materialType) {
        stockMapper.updateMaterialType(mtCode, materialType);
    }

    /**
     * 입고 품질검사가 완료된 자재 목록 가져오기
     */
    @Override
    public List<MtVO> getCompletedInspectionMaterials() {
        return stockMapper.getCompletedInspectionMaterials();
    }

//    /**
//     * 특정 자재 코드와 수량을 기반으로 현재 재고 업데이트
//     */
//    @Override
//    public void updateCurrentStock(String mtCode, Integer quantity) {
//        stockMapper.updateCurrentStock(mtCode, quantity);
//    }

    /**
     * 로트번호별 자재 수량 목록을 가져오는 메서드
     */
    @Override
    public List<MtInVO> getMaterialsByLotNo(String mtCode) {
        return stockMapper.getMaterialsByLotNo(mtCode);
    }

    /**
     * 로트번호별 자재 수량의 총합 조회
     */
    @Override
    public Integer getTotalQuantityByLotNo(String mtCode) {
        return stockMapper.getTotalQuantityByLotNo(mtCode);
    }

    /**
     * 특정 자재 코드를 기반으로 자재 정보 조회
     */
    @Override
    public MtVO selectMaterialByCode(String mtCode) {
        return stockMapper.getMaterialByCode(mtCode);
    }

    /**
     * 로트 번호에 수량을 추가하고 현재 재고를 업데이트
     */
    @Override
    public void addQuantityToLotAndUpdateStock(String mtCode, String lotNo, int quantity) {
        stockMapper.addQuantityToLotAndUpdateStock(mtCode, lotNo, quantity);  // 로트별 수량 업데이트
       
    }

    /**
     * 자재 코드에 따른 로트번호 목록을 조회하여 반환
     */
    @Override
    public List<String> getLotNumbersByMtCode(String mtCode) {
        return stockMapper.findLotNumbersByMtCode(mtCode);
    }
}
