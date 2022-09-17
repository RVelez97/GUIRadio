package classes;

import java.time.LocalDateTime;
public class RadioActivity {
	private Integer cod=0;
    private String name;
    private String description;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    public RadioActivity(String name, String description, LocalDateTime dateStart) {
        this.cod+=1;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}
