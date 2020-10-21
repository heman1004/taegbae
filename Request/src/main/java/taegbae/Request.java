package taegbae;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Request_table")
public class Request {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Long qty;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Requseted requseted = new Requseted();
        BeanUtils.copyProperties(this, requseted);
        requseted.publishAfterCommit();



        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        taegbae.external.Payment payment = new taegbae.external.Payment();
        // mappings goes here
        payment.setRequestId(this.getId());
        payment.setCustomerId(this.getCustomerId());
        payment.setStatus("Payment Completed");

        RequestApplication.applicationContext.getBean(taegbae.external.PaymentService.class)
            .doPay(payment);


    }

    @PostUpdate
    public void onPostUpdate(){
        Canceled canceled = new Canceled();
        BeanUtils.copyProperties(this, canceled);
        canceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
