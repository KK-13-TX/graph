package com.graph.api.utests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Graph API test class - entry class
 * 
 * @author khadidja
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ SimpleGraphTest.class, EmployeeGraphTest.class })
public class GraphTest {

}
