package nature.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private long id;
    private String URL;
    private String Title;
    private int Index_id;
    private java.sql.Date Recevied;
    private java.sql.Date Accepted;
    private java.sql.Date Published_online;
    private int Web_of_Science;
    private int CrossRef;
    private int Scopus;
    private int Altmetric;
    private double weight;

}

