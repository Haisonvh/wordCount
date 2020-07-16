
package hoangvu.wordcount.mvp;

import hoangvu.system.Constants;
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

    public void setFilePath(String filePath) throws NullPointerException{
        if (filePath == null)
            throw new NullPointerException(Constants.ERROR_NULL_FILE_PATH);
        this.filePath = filePath;
    }

    public Map<String, Long> getCounterMap() {
        return counterMap;
    }

    public void setCounterMap(Map<String, Long> counterMap) throws NullPointerException{
        if (counterMap == null)
            throw new NullPointerException(Constants.ERROR_NULL_MAP_DATA);
        this.counterMap = counterMap;
    }
    
}
