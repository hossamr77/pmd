/*
 * User: tom
 * Date: Jun 14, 2002
 * Time: 1:18:30 PM
 */
package test.net.sourceforge.pmd;

import junit.framework.TestCase;
import net.sourceforge.pmd.renderers.Renderer;
import net.sourceforge.pmd.renderers.XMLRenderer;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.ReportListener;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ReportTest extends TestCase implements ReportListener {

    private boolean semaphore;

    public ReportTest(String name) {
        super(name);
    }

    public void testBasic() {
        Report r = new Report();
        r.addRuleViolation(new RuleViolation(new MockRule(), 5, "foo"));
        assertTrue(!r.isEmpty());
    }

    // Files are grouped together now.
    public void testSortedReport_File() {
        Report r = new Report();
        r.addRuleViolation(new RuleViolation(new MockRule(), 10, "foo"));
        r.addRuleViolation(new RuleViolation(new MockRule(), 20, "bar"));
        Renderer rend = new XMLRenderer();
        String result = rend.render(r);
        assertTrue(result.indexOf("bar") < result.indexOf("foo"));
    }

    public void testSortedReport_Line() {
        Report r = new Report();
        r.addRuleViolation(new RuleViolation(new MockRule("rule2", "rule2"), 
					     10, "foo"));
        r.addRuleViolation(new RuleViolation(new MockRule("rule1", "rule1"), 
					     20, "foo"));
        Renderer rend = new XMLRenderer();
        String result = rend.render(r);
        assertTrue(result.indexOf("rule2") < result.indexOf("rule1"));
    }

    public void testListener() {
        Report rpt  = new Report();
        rpt.addListener(this);
        semaphore = false;
        rpt.addRuleViolation(new RuleViolation(new MockRule(), 5, "file"));
        assertTrue(semaphore);

    }

    public void ruleViolationAdded(RuleViolation ruleViolation) {
        semaphore = true;
    }

}
