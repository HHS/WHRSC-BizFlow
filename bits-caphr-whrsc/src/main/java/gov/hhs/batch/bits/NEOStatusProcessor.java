package gov.hhs.batch.bits;

import org.springframework.batch.item.ItemProcessor;

//Add logic for business rules for processing data before inserting into target table
public class NEOStatusProcessor implements ItemProcessor<ViewNEOStatusSource, TableNEOStatusTarget> {

    public TableNEOStatusTarget process(ViewNEOStatusSource neoStatusSourceRecord) throws Exception {
    	
    	TableNEOStatusTarget neoStatusTargetRecord = new TableNEOStatusTarget();
    	
    	neoStatusTargetRecord.setHHSID(neoStatusSourceRecord.getHHSID());
    	neoStatusTargetRecord.setDATE_REQUESTED(neoStatusSourceRecord.getDateRequested());
    	neoStatusTargetRecord.setSENSITIVITY_LEVEL(neoStatusSourceRecord.getSensitivityLevel());
    	neoStatusTargetRecord.setSENSITIVITY_LEVEL_DESC(neoStatusSourceRecord.getSensitivityLevelDesc());
    	neoStatusTargetRecord.setSENSITIVITY_LEVEL_FORMAL_DESC(neoStatusSourceRecord.getSensitivityLevelFormalDesc());
    	neoStatusTargetRecord.setCLEARED_FOR_NEO(neoStatusSourceRecord.getClearedForNEO());
    	neoStatusTargetRecord.setINACTIVE_DATE(neoStatusSourceRecord.getInactiveDate());
        return neoStatusTargetRecord;
    }



}
