package raspberry.Acquaintance;

import java.util.List;

public interface ILiveDataGetter {
    ErrorCode apply(List<String> data);
}
