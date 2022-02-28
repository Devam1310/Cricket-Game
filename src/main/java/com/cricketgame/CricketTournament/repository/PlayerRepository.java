package com.cricketgame.CricketTournament.repository;

import com.cricketgame.CricketTournament.beans.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends ElasticsearchRepository<Player, String> {
    Player[] findByPlayerTeam(String playerTeam);

}
