package ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.*;

import java.sql.Timestamp;

/**
 * Created by Olivier on 5/26/2016.
 */
public class DummyPrefPageLogicImpl implements PrefPageLogic{
    @Override
    public PrefSaveResult savePrefChoices(PrefSaveAction psa) {
        PrefSaveResult res = new PrefSaveResult();
        res.setSaveSuccessful(true);
        return res;
    }

    @Override
    public PrefPageInfosResult getPrefInfos() {
        PrefPageInfosResult res = new PrefPageInfosResult();

        AppID app1 = new AppID();
        app1.setAppShortName("S6-APP1");
        app1.setAppLongName("Nom complet de l'app1");

        TimeBlock block1 = new TimeBlock();
        block1.setStartTime(Timestamp.valueOf("2016-05-30 09:00:00.0"));
        block1.setEndTime(Timestamp.valueOf("2016-05-30 10:30:00.0"));
        block1.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        TimeBlock block2 = new TimeBlock();
        block2.setStartTime(Timestamp.valueOf("2016-05-30 10:30:00.0"));
        block2.setEndTime(Timestamp.valueOf("2016-05-30 12:00:00.0"));
        block2.setChoice(TimeBlock.BlockChoice.THIRD_CHOICE);

        TimeBlock block3 = new TimeBlock();
        block3.setStartTime(Timestamp.valueOf("2016-05-30 13:00:00.0"));
        block3.setEndTime(Timestamp.valueOf("2016-05-30 14:30:00.0"));
        block3.setChoice(TimeBlock.BlockChoice.FIRST_CHOICE);

        TimeBlock block4 = new TimeBlock();
        block4.setStartTime(Timestamp.valueOf("2016-05-30 14:30:00.0"));
        block4.setEndTime(Timestamp.valueOf("2016-05-30 16:00:00.0"));
        block4.setChoice(TimeBlock.BlockChoice.SECOND_CHOICE);

        AppPrefs appPrefs1 = new AppPrefs();
        appPrefs1.setAppId(app1);
        appPrefs1.getBlocks().add(block1);
        appPrefs1.getBlocks().add(block2);
        appPrefs1.getBlocks().add(block3);
        appPrefs1.getBlocks().add(block4);

        AppID app2 = new AppID();
        app2.setAppShortName("S6-APP2");
        app2.setAppLongName("Nom complet de l'app2");

        block1 = new TimeBlock();
        block1.setStartTime(Timestamp.valueOf("2016-06-06 09:00:00.0"));
        block1.setEndTime(Timestamp.valueOf("2016-06-06 10:30:00.0"));
        block1.setChoice(TimeBlock.BlockChoice.FIRST_CHOICE);

        block2 = new TimeBlock();
        block2.setStartTime(Timestamp.valueOf("2016-06-06 10:30:00.0"));
        block2.setEndTime(Timestamp.valueOf("2016-06-06 12:00:00.0"));
        block2.setChoice(TimeBlock.BlockChoice.SECOND_CHOICE);

        block3 = new TimeBlock();
        block3.setStartTime(Timestamp.valueOf("2016-06-06 13:00:00.0"));
        block3.setEndTime(Timestamp.valueOf("2016-06-06 14:30:00.0"));
        block3.setChoice(TimeBlock.BlockChoice.THIRD_CHOICE);

        block4 = new TimeBlock();
        block4.setStartTime(Timestamp.valueOf("2016-06-06 14:30:00.0"));
        block4.setEndTime(Timestamp.valueOf("2016-06-06 16:00:00.0"));
        block4.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        AppPrefs appPrefs2 = new AppPrefs();
        appPrefs2.setAppId(app2);
        appPrefs2.getBlocks().add(block1);
        appPrefs2.getBlocks().add(block2);
        appPrefs2.getBlocks().add(block3);
        appPrefs2.getBlocks().add(block4);

        AppID app3 = new AppID();
        app3.setAppShortName("S6-APP3");
        app3.setAppLongName("Nom complet de l'app3");

        block1 = new TimeBlock();
        block1.setStartTime(Timestamp.valueOf("2016-06-13 09:00:00.0"));
        block1.setEndTime(Timestamp.valueOf("2016-06-13 10:30:00.0"));
        block1.setChoice(TimeBlock.BlockChoice.THIRD_CHOICE);

        block2 = new TimeBlock();
        block2.setStartTime(Timestamp.valueOf("2016-06-13 10:30:00.0"));
        block2.setEndTime(Timestamp.valueOf("2016-06-13 12:00:00.0"));
        block2.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        block3 = new TimeBlock();
        block3.setStartTime(Timestamp.valueOf("2016-06-13 13:00:00.0"));
        block3.setEndTime(Timestamp.valueOf("2016-06-13 14:30:00.0"));
        block3.setChoice(TimeBlock.BlockChoice.FIRST_CHOICE);

        block4 = new TimeBlock();
        block4.setStartTime(Timestamp.valueOf("2016-06-13 14:30:00.0"));
        block4.setEndTime(Timestamp.valueOf("2016-06-13 16:00:00.0"));
        block4.setChoice(TimeBlock.BlockChoice.SECOND_CHOICE);

        AppPrefs appPrefs3 = new AppPrefs();
        appPrefs3.setAppId(app3);
        appPrefs3.getBlocks().add(block1);
        appPrefs3.getBlocks().add(block2);
        appPrefs3.getBlocks().add(block3);
        appPrefs3.getBlocks().add(block4);

        AppID app4 = new AppID();
        app4.setAppShortName("S6-APP4");
        app4.setAppLongName("Nom complet de l'app4");

        block1 = new TimeBlock();
        block1.setStartTime(Timestamp.valueOf("2016-06-20 09:00:00.0"));
        block1.setEndTime(Timestamp.valueOf("2016-06-20 10:30:00.0"));
        block1.setChoice(TimeBlock.BlockChoice.SECOND_CHOICE);

        block2 = new TimeBlock();
        block2.setStartTime(Timestamp.valueOf("2016-06-20 10:30:00.0"));
        block2.setEndTime(Timestamp.valueOf("2016-06-20 12:00:00.0"));
        block2.setChoice(TimeBlock.BlockChoice.THIRD_CHOICE);

        block3 = new TimeBlock();
        block3.setStartTime(Timestamp.valueOf("2016-06-20 13:00:00.0"));
        block3.setEndTime(Timestamp.valueOf("2016-06-20 14:30:00.0"));
        block3.setChoice(TimeBlock.BlockChoice.FIRST_CHOICE);

        block4 = new TimeBlock();
        block4.setStartTime(Timestamp.valueOf("2016-06-20 14:30:00.0"));
        block4.setEndTime(Timestamp.valueOf("2016-06-20 16:00:00.0"));
        block4.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        AppPrefs appPrefs4 = new AppPrefs();
        appPrefs4.setAppId(app4);
        appPrefs4.getBlocks().add(block1);
        appPrefs4.getBlocks().add(block2);
        appPrefs4.getBlocks().add(block3);
        appPrefs4.getBlocks().add(block4);

        AppID app5 = new AppID();
        app5.setAppShortName("S6-APP5");
        app5.setAppLongName("Nom complet de l'app5");

        block1 = new TimeBlock();
        block1.setStartTime(Timestamp.valueOf("2016-08-08 09:00:00.0"));
        block1.setEndTime(Timestamp.valueOf("2016-08-08 10:30:00.0"));
        block1.setChoice(TimeBlock.BlockChoice.THIRD_CHOICE);

        block2 = new TimeBlock();
        block2.setStartTime(Timestamp.valueOf("2016-08-08 10:30:00.0"));
        block2.setEndTime(Timestamp.valueOf("2016-08-08 12:00:00.0"));
        block2.setChoice(TimeBlock.BlockChoice.SECOND_CHOICE);

        block3 = new TimeBlock();
        block3.setStartTime(Timestamp.valueOf("2016-08-08 13:00:00.0"));
        block3.setEndTime(Timestamp.valueOf("2016-06-27 14:30:00.0"));
        block3.setChoice(TimeBlock.BlockChoice.FIRST_CHOICE);

        block4 = new TimeBlock();
        block4.setStartTime(Timestamp.valueOf("2016-08-08 14:30:00.0"));
        block4.setEndTime(Timestamp.valueOf("2016-08-08 16:00:00.0"));
        block4.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        AppPrefs appPrefs5 = new AppPrefs();
        appPrefs5.setAppId(app5);
        appPrefs5.getBlocks().add(block1);
        appPrefs5.getBlocks().add(block2);
        appPrefs5.getBlocks().add(block3);
        appPrefs5.getBlocks().add(block4);

        AppID app6 = new AppID();
        app6.setAppShortName("S6-APP6");
        app6.setAppLongName("Nom complet de l'app6");

        block1 = new TimeBlock();
        block1.setStartTime(Timestamp.valueOf("2016-08-15 09:00:00.0"));
        block1.setEndTime(Timestamp.valueOf("2016-08-15 10:30:00.0"));
        block1.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        block2 = new TimeBlock();
        block2.setStartTime(Timestamp.valueOf("2016-08-15 10:30:00.0"));
        block2.setEndTime(Timestamp.valueOf("2016-08-15 12:00:00.0"));
        block2.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        block3 = new TimeBlock();
        block3.setStartTime(Timestamp.valueOf("2016-08-15 13:00:00.0"));
        block3.setEndTime(Timestamp.valueOf("2016-08-15 14:30:00.0"));
        block3.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        block4 = new TimeBlock();
        block4.setStartTime(Timestamp.valueOf("2016-08-15 14:30:00.0"));
        block4.setEndTime(Timestamp.valueOf("2016-08-15 16:00:00.0"));
        block4.setChoice(TimeBlock.BlockChoice.NO_CHOICE);

        AppPrefs appPrefs6 = new AppPrefs();
        appPrefs6.setAppId(app6);
        appPrefs6.getBlocks().add(block1);
        appPrefs6.getBlocks().add(block2);
        appPrefs6.getBlocks().add(block3);
        appPrefs6.getBlocks().add(block4);

        res.getApps().add(appPrefs1);
        res.getApps().add(appPrefs2);
        res.getApps().add(appPrefs3);
        res.getApps().add(appPrefs4);
        res.getApps().add(appPrefs5);
        res.getApps().add(appPrefs6);

        return res;
    }
}
