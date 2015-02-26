package com.jt.cabin.sql;

/**
 * @author ze.liu
 * @since 2014/5/16
 */
public class SqlSample {

    /**
     * @Service public class ITTSDataServiceImpl implements ITTSDataService {
    private static final String QUERY_SQL="select host,port,databaseName,clientId,config,order_prefix from tts.client_shard ";
     @Autowired
     @Qualifier("ttsDataJDBCTemplate")
     JdbcTemplate jdbcTemplate;
     @Override public List<ClientShard> findAllInterClientShards() {
     return jdbcTemplate.query(QUERY_SQL, new RowMapper<ClientShard>(){
     @Override public ClientShard mapRow(ResultSet rs, int rowNum) throws SQLException {
     ClientShard clientShard=new ClientShard();
     clientShard.setHost(rs.getString("host"));
     clientShard.setPort(rs.getString("port"));
     clientShard.setDatabaseName(rs.getString("databaseName"));
     clientShard.setClientId(rs.getString("clientId"));
     clientShard.setConfig(rs.getInt("config"));
     clientShard.setOrderPrefix(rs.getString("order_prefix"));
     return clientShard;
     }
     });
     }
     }
     */
}
