package org.garage48.travellinn;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.IBinder;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;

import java.util.ArrayList;
import java.util.List;

public class SampleChooserTargetService extends ChooserTargetService {
    @Override
    public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName, IntentFilter matchedFilter) {
        final List<ChooserTarget> targets = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            final String targetName = "";
            final Icon targetIcon = null;
            final float targetRanking = 0;
            final ComponentName targetComponentName = null;
            final Bundle targetExtras = null;
            targets.add(new ChooserTarget(
                    targetName, targetIcon, targetRanking, targetComponentName, targetExtras
            ));
        }
        return targets;
    }
}