/**
 * Copyright (C) 2014 Next Generation Mobile Service JSC., (NMS). All rights reserved.
 */
package com.nms.vnm.eip.service;

import com.nms.vnm.eip.entity.Product;
import java.io.Serializable;

public interface ChargingService extends Serializable {

    public int chargeProduct(Product product);
}
