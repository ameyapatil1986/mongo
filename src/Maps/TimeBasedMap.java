package Maps;

import java.util.*;


/**
 * https://leetcode.com/problems/time-based-key-value-store/
 */
public class TimeBasedMap {

    Map<String, List<TimeValue>> map;

    public TimeBasedMap() {
        map = new HashMap<>();
    }

    private class TimeValue {
        int time;
        String value;

        TimeValue(int time, String value) {
            this.time = time;
            this.value = value;
        }

        public int getTime() {
            return time;
        }

        public String getValue() {
            return value;
        }

        @Override
        public boolean equals(final Object pO) {
            if (this == pO) {
                return true;
            }
            if (pO == null || getClass() != pO.getClass()) {
                return false;
            }
            final TimeValue timeValue = (TimeValue) pO;
            return time == timeValue.time && Objects.equals(value, timeValue.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, value);
        }
    }



    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<TimeValue>()).add(new TimeValue(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        int index = binarySearchModified(map.get(key), timestamp);

        return map.get(key).get(index).getValue();
    }

    private int binarySearchModified(List<TimeValue> list, int timestamp) {
        int lb = 0;
        int hb = 0;

        while (lb <= hb) {
            int mid = (lb + hb)/2;

            if (list.get(mid).getTime() == timestamp) {
                return mid;
            }

            if (list.get(mid).getTime() < timestamp && (mid == list.size() - 1 || timestamp < list.get(mid + 1).getTime())) {
                return mid;
            }

            if (list.get(mid).getTime() < timestamp) {
                lb = mid + 1;
            } else {
                hb = mid - 1;
            }
        }

        return -1;
    }
}
