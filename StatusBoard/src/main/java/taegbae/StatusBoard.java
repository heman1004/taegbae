package taegbae;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="StatusBoard_table")
public class StatusBoard {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long requstId;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getRequstId() {
            return requstId;
        }

        public void setRequstId(Long requstId) {
            this.requstId = requstId;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
