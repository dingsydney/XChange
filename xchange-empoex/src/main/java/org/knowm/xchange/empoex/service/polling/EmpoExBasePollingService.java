package org.knowm.xchange.empoex.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.empoex.EmpoEx;
import org.knowm.xchange.empoex.EmpoExAuthenticated;
import org.knowm.xchange.empoex.service.EmpoExHmacPostBodyDigest;
import org.knowm.xchange.empoex.service.EmpoExPayloadDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class EmpoExBasePollingService extends BaseExchangeService implements BasePollingService {

  protected final String apiKey;
  protected final EmpoExAuthenticated empoExAuthenticated;
  protected final ParamsDigest signatureCreator;
  protected final ParamsDigest payloadCreator;

  protected final EmpoEx empoEx;

  /**
   * Constructor
   *
   * @param exchange
   */
  public EmpoExBasePollingService(Exchange exchange) {

    super(exchange);

    this.empoExAuthenticated = RestProxyFactory.createProxy(EmpoExAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator = EmpoExHmacPostBodyDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    this.payloadCreator = new EmpoExPayloadDigest();

    this.empoEx = RestProxyFactory.createProxy(EmpoEx.class, exchange.getExchangeSpecification().getSslUri());

  }

}
