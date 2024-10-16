package springjdbc.oracle;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import springjdbc.oracle.models.CorrelationId;
import springjdbc.oracle.models.ListBranch;
import springjdbc.oracle.models.StatusHistory;
import springjdbc.oracle.models.TerrabateFile;

import java.util.List;

public class DataAccessObjectOracle {

    private final JdbcTemplate jdbcTemplate;

    public DataAccessObjectOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

        public List<TerrabateFile> getRecentTerrabyteXmlFiles() {
            String sql = "SELECT OBJECT_ID, FILE_MNEMONIC, CREATED " +
                    "FROM ( " +
                    "    SELECT f.OBJECT_ID, f.FILE_MNEMONIC, f.CREATED, ROWNUM rnum " +
                    "    FROM ( " +
                    "        SELECT f.OBJECT_ID, f.FILE_MNEMONIC, f.CREATED " +
                    "        FROM terrabyte.tbs_file_object f " +
                    "        JOIN terrabyte.tbs_metafile m ON m.FILE_OBJ_ID = f.id " +
                    "        JOIN terrabyte.tbs_attribute a ON a.METAFILE_ID = m.META_ID " +
                    "        WHERE EXISTS ( " +
                    "            SELECT 1 " +
                    "            FROM terrabyte.tbs_file_object " +
                    "            WHERE object_id = f.object_id AND file_mnemonic = f.file_mnemonic " +
                    "        ) " +
                    "        AND f.OBJECT_TYPE_ID = '103' AND f.DELETED = 'N' AND a.FILE_EXT = 'xml' " +
                    "        ORDER BY f.created DESC " +
                    "    ) f " +
                    "    WHERE ROWNUM <= 10 " +
                    ") " +
                    "WHERE rnum > 0";

            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TerrabateFile.class));
        }

        public List<ListBranch> getKnoBranchNames() {
            return jdbcTemplate.query("select name from nsi.do_kno_branch",
                    new BeanPropertyRowMapper<>(ListBranch.class));
        }

        public List<ListBranch> findKnoBranchByOgrn(String ogrn) {
            String sql = "select ogrn from nsi.do_kno_branch where ogrn = ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListBranch.class), ogrn);
        }

        public void deleteKnoBranchByOgrn(String ogrn) {
            String sql = "delete from nsi.do_kno_branch where ogrn = ?";
            jdbcTemplate.update(sql, ogrn);
        }

        public List<CorrelationId> findOrderCorrelationIdsByOgrn(String ogrn) {
            String sql = "select distinct sreq.ID, slog.* FROM LK.SC_ORDER_QUEUE_LOG slog " +
                    "join LK.SC_SMEV3_REQUEST sreq ON slog.SCOQ_ID = sreq.REQUESTID " +
                    "where slog.ORDER_ID = ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CorrelationId.class), ogrn);
        }

        public List<StatusHistory> getOrderStatusHistory(String orderId) {
            String sql = "select order_status_id from lk.status_history where order_id = ? order by status_date desc";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StatusHistory.class), orderId);
        }
    }



