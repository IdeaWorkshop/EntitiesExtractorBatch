package org.idea.analytics.reader;

import org.idea.analytics.model.Url;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class UrlReader extends FlatFileItemReader<Url>{

    public UrlReader() {
        setResource(new ClassPathResource("/urls.csv"));

        DefaultLineMapper<Url> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"url"});
        lineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<Url> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Url.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        setLineMapper(lineMapper);
    }
}
