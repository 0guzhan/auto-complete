package net.acargil.search;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AutoCompleteSearchIndexImplTest.class, WordPermutationTest.class,
    BreadCrumbSearchIndexTest.class})
public class AllTests {

}
