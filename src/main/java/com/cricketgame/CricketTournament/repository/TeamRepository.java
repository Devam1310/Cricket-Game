package com.cricketgame.CricketTournament.repository;

import com.cricketgame.CricketTournament.beans.Team;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ElasticsearchRepository<Team, String> {
    Team findByTeamName(String teamName);
}
