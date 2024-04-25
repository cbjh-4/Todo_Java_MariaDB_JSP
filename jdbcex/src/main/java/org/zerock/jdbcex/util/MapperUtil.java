package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;
    private ModelMapper modelMapper;
    MapperUtil(){
        modelMapper = new ModelMapper();
        // ModelMapper의 설정을 조정합니다.
        modelMapper.getConfiguration()
                // setFieldMatchingEnabled(true) 설정은 필드 매칭을 활성화합니다. 이는 소스 객체와 대상 객체 간에 이름과 타입이 일치하는 필드를 자동으로 매핑하도록 합니다.
                .setFieldMatchingEnabled(true)
                // setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)는 ModelMapper에게 private 필드에도 접근하여 매핑할 수 있도록 허용합니다.
                // Java에서는 기본적으로 private 필드에는 외부에서 접근할 수 없지만, ModelMapper는 리플렉션을 사용하여 이러한 접근 제한을 우회합니다.
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                // setMatchingStrategy(MatchingStrategies.STRICT)는 매핑 전략을 엄격하게(STRICT) 설정합니다.
                // 엄격한 매칭 전략을 사용하면, 이름이 정확히 일치하는 필드들만 매핑됩니다. 이는 이름이 비슷하거나 부분적으로 일치하는 필드 사이의 잘못된 매핑을 방지해줍니다.
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ModelMapper get() {return modelMapper;}
}
