package com.bottlerocketstudios.continuitysample.legislator.usecase;

import com.bottlerocketstudios.continuitysample.core.usecase.BaseAgent;
import com.bottlerocketstudios.continuitysample.legislator.domain.LegislatorRepository;
import com.bottlerocketstudios.continuitysample.legislator.model.Legislator;

import java.util.List;

/**
 * Created on 9/14/16.
 */
public class GetFavoriteLegislatorsAgent extends BaseAgent<List<Legislator>> {

    private final LegislatorRepository mLegislatorRepository;

    public GetFavoriteLegislatorsAgent(LegislatorRepository legislatorRepository) {
        mLegislatorRepository = legislatorRepository;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void run() {
        notifyCompletion(mLegislatorRepository.getFavoriteLegislators());
    }
}
