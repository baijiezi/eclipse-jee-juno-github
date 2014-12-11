package com.sun.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.common.BaseConditionVO;
import com.sun.dao.AutoPayMapper;
import com.sun.entity.AutoPayEntity;
import com.sun.entity.TransActionEntity;

@Service("autopayService")
public class AutoPayServiceImp implements AutoPayServiceI {
	
	private AutoPayMapper autoPayMapper;
	
	public AutoPayMapper getAutoPayMapper() {
		return autoPayMapper;
	}
	@Autowired
	public void setAutoPayMapper(AutoPayMapper autoPayMapper) {
		this.autoPayMapper = autoPayMapper;
	}

	public List<AutoPayEntity> getAll() {
		return autoPayMapper.getAll();
	}
//	public String downloadOrder(OrderInfoosModel model11,List<Integer> orderList) throws IOException, WriteException {
//		File ctxFile = new File(SUPPLIER_ORDERS_UPLOAD_PATH);
//		if (!ctxFile.isDirectory()) {
//			ctxFile.mkdirs();
//		}
//		// �ļ��Ĵ洢�����·��
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String ctxPath = "����-" + sdf.format(new Date()).toString() + ".xls";
//		String filePath = SUPPLIER_ORDERS_UPLOAD_PATH + ctxPath;
//		// �½���һ��jxl�ļ�,����e��������testJXL.xls
//		OutputStream os = new FileOutputStream(filePath);
//		WritableWorkbook wwb = Workbook.createWorkbook(os);
//		WritableSheet sheet = wwb.createSheet("ȫ������", 0);
//		
//		Label label = null;
//		createHead(label, sheet);
//		List<OrderInfoosBean> dataList = autoPayMapper.getAll();
//		createData(label, sheet, dataList);
//
//		// ����
//				int n = 1;
//				for (int i = 0; i < orderList.size(); i++) {
//					model.setId(orderList.get(i));
//					dataList = mapper.findAllByModel(model);
//					sheet = wwb.createSheet(dataList.get(0).getWareHouseName(), n);
//					createHead(label, sheet);
//					createData(label, sheet, dataList);
//					n++;
//				}
//				wwb.write();
//				// �ر��ļ�
//				wwb.close();
//				os.close();
//				return filePath;
//	}
	public List<AutoPayEntity> getWhereEntity(AutoPayEntity autoPayEntity) {
		// TODO Auto-generated method stub
		return autoPayMapper.getWhereEntity(autoPayEntity);
	}
	public AutoPayEntity getAutoById(AutoPayEntity autoPayEntity) {
		// TODO Auto-generated method stub
		return autoPayMapper.getAutoById(autoPayEntity);
	}
	public int insert(AutoPayEntity autoPayEntity) {
		// TODO Auto-generated method stub
		return autoPayMapper.insert(autoPayEntity);
	}
	public List<AutoPayEntity> searchAutoPay(BaseConditionVO vo) {
		List<AutoPayEntity> bos = new ArrayList<AutoPayEntity>();
		RowBounds rb = new RowBounds(vo.getStartIndex(), vo.getPageSize());
		List<AutoPayEntity> pos = autoPayMapper.findPageBreakByCondition(vo, rb);
		return pos;
	}
	public int getCout(AutoPayEntity transActionEntity) {
		// TODO Auto-generated method stub
		return autoPayMapper.getCout(transActionEntity);
	}

}
