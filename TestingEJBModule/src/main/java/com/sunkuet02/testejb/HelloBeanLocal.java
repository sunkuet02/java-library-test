/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunkuet02.testejb;

import javax.ejb.Local;

/**
 *
 * @author sun
 */
@Local
public interface HelloBeanLocal {
    String sayHello(String name);
}
