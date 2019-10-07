package purplestudio.androidbasics.lib;

import purplestudio.androidbasics.lib.service.LifeCycle;

public interface Listener extends LifeCycle
{
    void fire();
}
