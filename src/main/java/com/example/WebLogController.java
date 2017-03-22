package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Description("A controller to accept incoming http web log access")
public class WebLogController implements BeanFactoryAware {

  private BeanFactory context;

  @Override
  public void setBeanFactory(BeanFactory factory) {
    this.context = factory;
  }

  @RequestMapping(value = "/weblogs", method = RequestMethod.POST)
  @ResponseBody
  public String logs(@RequestBody String body) throws IOException {
    MessageChannel toKafka = context.getBean("toKafka", MessageChannel.class);
    toKafka.send(new GenericMessage<>(body));
    return HttpStatus.OK.toString();
  }
}