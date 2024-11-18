package com.tech.task.report.repository.report;

import com.tech.task.report.entity.report.SalesTrafficReportEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesTrafficReportRepository extends MongoRepository<SalesTrafficReportEntity, String> {

    @Cacheable(value = "findStatisticsByDateRange", key = "#startDate.toString() + '-' + #endDate?.toString()")
    @Query(value = "{'salesAndTrafficByDate.date': { $gte: ?0, $lte: ?1 }}",
            fields = "{'salesAndTrafficByDate': {$filter: {input: '$salesAndTrafficByDate', as: 'item', cond: {$and: [{ $gte:['$$item.date', ?0]}, {$lte: ['$$item.date', ?1]}]}}}, '_id': 0}")
    List<SalesTrafficReportEntity> findStatisticsByDateRange(Date startDate, Date endDate);

    @Cacheable(value = "findStatisticsBySingleDate", key = "#startDate.toString()")
    @Query(value = "{'salesAndTrafficByDate.date': ?0}",
            fields = "{'salesAndTrafficByDate': {$elemMatch: {'date': ?0}}, '_id': 0}")
    List<SalesTrafficReportEntity> findStatisticsBySingleDate(Date startDate);

    @Cacheable(value = "findStatisticsByAsinIn", key = "#parentAsins")
    @Query(value = "{'salesAndTrafficByAsin.parentAsin': {$in: ?0}}",
            fields = "{'salesAndTrafficByAsin': {$filter: {input: '$salesAndTrafficByAsin', as: 'item', cond: { $in: ['$$item.parentAsin', ?0]}}}, '_id': 0}")
    List<SalesTrafficReportEntity> findStatisticsByAsinIn(List<String> parentAsins);

    @Cacheable(value = "findAllStatisticsByDates")
    @Query(value = "{}",
            fields = "{'salesAndTrafficByDate': 1, '_id': 0}")
    List<SalesTrafficReportEntity> findAllStatisticsByDates();

    @Cacheable(value = "findAllStatisticsByAsins")
    @Query(value = "{}",
            fields = "{'salesAndTrafficByAsin': 1, '_id': 0}")
    List<SalesTrafficReportEntity> findAllStatisticsByAsins();

    Optional<SalesTrafficReportEntity> findTopByOrderByIdDesc();
}
