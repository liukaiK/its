package cn.com.goodlan.its.core.pojo.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 短信历史记录
 *
 * @author liukai
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SmsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    private String content;

    private String result;

    public SmsHistory(String phoneNumber, String content, String result) {
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.result = result;
    }

}
