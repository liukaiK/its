package cn.com.goodlan.its.core.pojo.entity.primary.event;

import cn.com.goodlan.its.core.pojo.entity.primary.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 白名单
 *
 * @author liukai
 */
@Setter
@Getter
@Entity
@Table(name = "eve_whitelist")
public class Whitelist extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String licensePlateNumber;

}
