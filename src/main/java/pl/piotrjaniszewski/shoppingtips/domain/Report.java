package pl.piotrjaniszewski.shoppingtips.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private ReportedObject reportedObject;

    private Long reportedObjectId;
    private String reason;
}
