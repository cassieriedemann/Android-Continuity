package com.bottlerocketstudios.continuitysample.legislator.usecase;

import com.bottlerocketstudios.continuitysample.core.usecase.BaseAgent;
import com.bottlerocketstudios.continuitysample.legislator.domain.LegislatorRepository;
import com.bottlerocketstudios.continuitysample.legislator.model.Legislator;

import java.util.List;

/**
 * Created on 9/14/16.
 */
public class GetLegislatorsByNameAgent extends BaseAgent<List<Legislator>> {

    private final LegislatorRepository mLegislatorRepository;
    private final String mName;

    public GetLegislatorsByNameAgent(LegislatorRepository legislatorRepository, String name) {
        mLegislatorRepository = legislatorRepository;
        mName = name;
    }

    @Override
    public String createUniqueIdentifier() {
        return super.createUniqueIdentifier() + mName;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void run() {
        notifyCompletion(mLegislatorRepository.fetchLegislatorListByName(mName));
    }
}
