package org.sai.play.dao;

import org.sai.play.model.Customer;

/**
 * Created by IntelliJ IDEA.
 * User: Sai
 * Date: 08/10/11
 * Time: 09:26
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerDao {
  Customer findById(Long id);
}
