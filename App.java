import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    static class DataItem {

        private Integer meterId;

        private String dataItem;

        private LocalDateTime tv;

        private String status;

        public Integer getMeterId() {
            return meterId;
        }

        public void setMeterId(Integer meterId) {
            this.meterId = meterId;
        }

        public String getDataItem() {
            return dataItem;
        }

        public void setDataItem(String dataItem) {
            this.dataItem = dataItem;
        }

        public LocalDateTime getTv() {
            return tv;
        }

        public void setTv(LocalDateTime tv) {
            this.tv = tv;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    private static final Integer BATCH_SIZE = 10000000;

    public static void main(String[] args) {
        List<DataItem> dataItemList = generateLargeData();
        System.out.println("数据规模：" + BATCH_SIZE);
        long start = System.currentTimeMillis();
        dataItemList.stream()
                .flatMap(dataItem -> doThing(dataItem).stream())
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    public static List<Map<String, Object>> doThing(DataItem dataItem) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("METER_ID", dataItem.getMeterId());
            map.put("DATA_ITEM", dataItem.getDataItem());
            map.put("TV", dataItem.getTv());
            map.put("STATUS", dataItem.getStatus());
            list.add(map);
        }
        return list;
    }

    public static List<DataItem> generateLargeData() {
        List<DataItem> dataItemList = new ArrayList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            DataItem dataItem = new DataItem();
            dataItem.setMeterId(i);
            dataItem.setDataItem("dataItem" + i);
            dataItem.setTv(LocalDateTime.now());
            dataItem.setStatus("status" + i);
            dataItemList.add(dataItem);
        }
        return dataItemList;
    }
}
