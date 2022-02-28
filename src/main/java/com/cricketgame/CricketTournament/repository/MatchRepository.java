package com.cricketgame.CricketTournament.repository;

import com.cricketgame.CricketTournament.beans.MatchInformation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends ElasticsearchRepository<MatchInformation, String> {

    MatchInformation findAllByWinningTeamName(String teamName);

    MatchInformation findAllByLoosingTeamName(String teamName);
}
