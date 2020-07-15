
package hoangvu.wordcount.mvp;

import java.util.Map;

/**
 * @author HoangVu
 */
public abstract class Model {
    private String filePath;
    private Map<String, Long> counterMap;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Long> getCounterMap() {
        return counterMap;
    }

    public void setCounterMap(Map<String, Long> counterMap) {
        this.counterMap = counterMap;
    }
    
}
