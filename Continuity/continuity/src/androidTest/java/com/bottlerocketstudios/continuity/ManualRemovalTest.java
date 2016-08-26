package com.bottlerocketstudios.continuity;

import android.support.test.runner.AndroidJUnit4;

import com.bottlerocketstudios.continuity.model.ContinuousTestClass;
import com.bottlerocketstudios.continuity.model.TestAnchor;
import com.bottlerocketstudios.continuity.util.SequentialNumberGenerator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created on 8/23/16.
 */
@RunWith(AndroidJUnit4.class)
public class ManualRemovalTest extends ContinuityTest {

    @Test
    public void testManualRemoval() {
        int taskId = SequentialNumberGenerator.generateNumber();
        TestAnchor testAnchor = new TestAnchor();

        ContinuousTestClass beforeRemoval = getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).build();

        getContinuityRepository().with(new TestAnchor(), ContinuousTestClass.class).task(taskId).remove();
        Assert.assertTrue("Instance was not notified of discard", beforeRemoval.isDiscarded());

        ContinuousTestClass afterRemoval = getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).build();

        Assert.assertNotEquals("Instance was retained after removal", beforeRemoval, afterRemoval);
    }

    @Test
    public void testDestroyAfterManualRemoval() {
        int taskId = SequentialNumberGenerator.generateNumber();
        TestAnchor testAnchor = new TestAnchor();

        ContinuousTestClass beforeRemoval = getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).build();

        getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).remove();
        //This just ensures that even if the testAnchor->List<ContainerId> exists without a corresponding ContainerId->ContinuousObject, there is no issue.
        getContinuityRepository().onDestroy(testAnchor);
        Assert.assertFalse("This shoud not have been notified of destruction.", beforeRemoval.isDestroyed());

        ContinuousTestClass afterRemoval = getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).build();

        Assert.assertNotEquals("Instance was retained after removal", beforeRemoval, afterRemoval);
    }

    @Test
    public void testDestroyThenRemove() {
        int taskId = SequentialNumberGenerator.generateNumber();
        TestAnchor testAnchor = new TestAnchor();

        ContinuousTestClass beforeRemoval = getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).build();

        getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).destroyThenRemove();
        Assert.assertTrue("Object was not notified of destruction.", beforeRemoval.isDestroyed());
        Assert.assertTrue("Object was not notified of discard.", beforeRemoval.isDiscarded());

        ContinuousTestClass afterRemoval = getContinuityRepository().with(testAnchor, ContinuousTestClass.class).task(taskId).build();

        Assert.assertNotEquals("Instance was retained after removal", beforeRemoval, afterRemoval);
    }
}
