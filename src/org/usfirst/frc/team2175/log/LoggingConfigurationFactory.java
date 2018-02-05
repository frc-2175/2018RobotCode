package org.usfirst.frc.team2175.log;

import java.net.URI;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

@Plugin(name = "LoggingConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(50)
public class LoggingConfigurationFactory extends ConfigurationFactory {
	private String logFolder;
	
	public LoggingConfigurationFactory(String logFolder) { 
		this.logFolder = logFolder;
	}
	
	Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(Level.ERROR);
        
        AppenderComponentBuilder appenderBuilder = builder.newAppender("ConsoleAppender", "CONSOLE").
            addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout").
            addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        builder.add(appenderBuilder);
        
        AppenderComponentBuilder jsonAppender = builder.newAppender("JSONAppender", "FILE")
        		.addAttribute("fileName", logFolder + "/" + "events.log");
        jsonAppender.add(builder.newLayout("JSONLayout")
        		.addAttribute("compact", "true")
        		.addAttribute("eventEol", "true"));
        jsonAppender.add(builder.newFilter("BurstFilter", Filter.Result.NEUTRAL, Filter.Result.DENY)
        		.addAttribute("level", "INFO")
        		.addAttribute("rate", "2"));
        builder.add(jsonAppender);
        
        AppenderComponentBuilder asyncAppender = builder.newAppender("AsyncAppender", "ASYNC")
        		.addAttribute("bufferSize", "80");
        asyncAppender.addComponent(builder.newAppenderRef("JSONAppender"));
        builder.add(asyncAppender);
        
        builder.add(builder.newLogger("FileLogger", Level.DEBUG)
        		.add(builder.newAppenderRef("AsyncAppender")));
        builder.add(builder.newRootLogger(Level.DEBUG)
        		.add(builder.newAppenderRef("ConsoleAppender")));
        
        return builder.build();
    }
	
	@Override
	public Configuration getConfiguration(LoggerContext arg0, ConfigurationSource arg1) {
		ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
		return createConfiguration(arg1.toString(), builder);
	}

	@Override
	protected String[] getSupportedTypes() {
		return new String[] {"*"};
	}

}
