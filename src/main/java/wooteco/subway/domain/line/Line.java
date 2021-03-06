package wooteco.subway.domain.line;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;

public class Line {
    @Id
    private Long id;

    private String name;

    private LocalTime startTime;

    private LocalTime endTime;

    private int intervalTime;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column("bg_color")
    private String backgroundColor;

    @Embedded.Empty
    private LineStations stations = LineStations.empty();

    private Line() {
    }

    public Line(Long id, String name, LocalTime startTime, LocalTime endTime, int intervalTime,
            String backgroundColor) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalTime = intervalTime;
        this.backgroundColor = backgroundColor;
    }

    public Line(String name, LocalTime startTime, LocalTime endTime, int intervalTime, String backgroundColor) {
        this(null, name, startTime, endTime, intervalTime, backgroundColor);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public Set<LineStation> getStations() {
        return stations.getStations();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(Line line) {
        if (line.getName() != null) {
            this.name = line.getName();
        }
        if (line.getStartTime() != null) {
            this.startTime = line.getStartTime();
        }
        if (line.getEndTime() != null) {
            this.endTime = line.getEndTime();
        }
        if (line.getIntervalTime() != 0) {
            this.intervalTime = line.getIntervalTime();
        }
    }

    public void addLineStation(LineStation lineStation) {
        stations.add(lineStation);
    }

    public void removeLineStationById(Long stationId) {
        stations.removeById(stationId);
    }

    public List<Long> getStationIds() {
        return stations.getStationIds();
    }
}
