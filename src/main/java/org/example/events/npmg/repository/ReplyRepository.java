package org.example.events.npmg.repository;

import jdk.jfr.Registered;
import org.example.events.npmg.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // TODO search for reply
}