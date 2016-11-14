package ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.*;

import java.sql.Timestamp;

/**
 * Created by Olivier on 6/19/2016.
 */
public class DummyGroupPageLogicImpl implements GroupPageLogic{
    @Override
    public GroupPageInfosResult getTutoGroups() {
        GroupPageInfosResult res = new GroupPageInfosResult();
        int appointmentId = 0;

        AppTutos app;
        TutoGroup group;
        AppID appID;
        Teacher teacher;
        Student student;
        TutoAppointment appointment;

        // APP1 T1
        app = new AppTutos();
        appID = new AppID();
        appID.setAppShortName("APP1");
        appID.setAppLongName("description of APP1");
        appID.setTeacher(true);
        app.setAppID(appID);

        teacher = new Teacher();
        teacher.setName("Elon Musk");
        teacher.setEmailAdd("elon.musk@usherbrooke.ca");
        app.getTeachers().add(teacher);
        teacher = new Teacher();
        teacher.setName("Jesus Christ");
        teacher.setEmailAdd("jesus.christ@usherbrooke.ca");
        app.getTeachers().add(teacher);
        teacher = new Teacher();
        teacher.setName("Black Sabbath");
        teacher.setEmailAdd("black.sabbath@usherbrooke.ca");
        app.getTeachers().add(teacher);

        group = new TutoGroup();
        group.setTutoId("T1");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-20 09:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-20 10:30:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-27 09:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-27 11:00:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-29 09:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-29 11:00:00.0"));
        appointment.setLabel("tutorat de fermeture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);

        student = new Student();
        student.setName("Agamemnon");
        student.setCip("Agam6666");
        student.setPresenceEntry(0,true);
        student.setPresenceEntry(14,true);
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Thor");
        student.setCip("thor2564");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setPresenceEntry(1,true);
        student.setResp(Student.Responsability.INTENDANT);
        group.getStudents().add(student);

        student = new Student();
        student.setName("René Lévesque");
        student.setCip("levr1387");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setPresenceEntry(2,true);
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Athena");
        student.setCip("athe8765");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.SCRIBE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Captain America");
        student.setCip("amec9999");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Aristote");
        student.setCip("aris5463");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.SECRETARY);
        group.getStudents().add(student);

        app.getTutos().add(group);

        // APP1 T2
        group = new TutoGroup();
        group.setTutoId("T2");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-20 10:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-20 12:00:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-29 11:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-29 12:30:00.0"));
        appointment.setLabel("tutorat de fermeture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);

        student = new Student();
        student.setName("Céline Dion");
        student.setCip("dioc9245");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        student.setPresenceEntry(3, true);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Justin Bieber");
        student.setCip("biej2110");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Denis Barbu");
        student.setCip("bard5476");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Erik Cartman");
        student.setCip("care9999");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Some Carebear");
        student.setCip("cars0000");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Donald Trump");
        student.setCip("trud4736");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Rambo");
        student.setCip("ramb3255");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        app.getTutos().add(group);

        // APP1 T3
        group = new TutoGroup();
        group.setTutoId("T3");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-20 13:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-20 14:30:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-06-29 13:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-06-29 15:00:00.0"));
        appointment.setLabel("tutorat de fermeture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);

        student = new Student();
        student.setName("Harry Potter");
        student.setCip("poth3285");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Gandalf the grey");
        student.setCip("greg6284");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Mickey Mouse");
        student.setCip("moum9836");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Mario Bros");
        student.setCip("brom7368");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Lucifer");
        student.setCip("luci3333");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        app.getTutos().add(group);
        res.getApps().add(app);

        // APP2 T1
        app = new AppTutos();
        appID = new AppID();
        appID.setAppShortName("APP2");
        appID.setAppLongName("description of APP2");
        app.setAppID(appID);

        teacher = new Teacher();
        teacher.setName("Professor Dumbledore");
        teacher.setEmailAdd("professor.dumbledore@usherbrooke.ca");
        app.getTeachers().add(teacher);
        teacher = new Teacher();
        teacher.setName("Jesus Christ");
        teacher.setEmailAdd("jesus.christ@usherbrooke.ca");
        app.getTeachers().add(teacher);

        group = new TutoGroup();
        group.setTutoId("T1");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-05 09:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-05 10:30:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-13 9:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-13 11:00:00.0"));
        appointment.setLabel("tutorat de fermeture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);

        student = new Student();
        student.setName("Agamemnon");
        student.setCip("Agam6666");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Thor");
        student.setCip("thor2564");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("René Lévesque");
        student.setCip("levr1387");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Athena");
        student.setCip("athe8765");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Captain America");
        student.setCip("amec9999");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Aristote");
        student.setCip("aris5463");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        app.getTutos().add(group);

        // APP2 T2
        group = new TutoGroup();
        group.setTutoId("T2");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-05 10:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-05 12:00:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-13 11:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-13 12:30:00.0"));
        appointment.setLabel("tutorat de fermeture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);

        student = new Student();
        student.setName("Céline Dion");
        student.setCip("dioc9245");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Justin Bieber");
        student.setCip("biej2110");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Denis Barbu");
        student.setCip("bard5476");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Erik Cartman");
        student.setCip("care9999");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Some Carebear");
        student.setCip("cars0000");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Donald Trump");
        student.setCip("trud4736");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Rambo");
        student.setCip("ramb3255");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        app.getTutos().add(group);

        // APP1 T3
        group = new TutoGroup();
        group.setTutoId("T3");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-05 13:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-05 14:30:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-13 13:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-13 15:00:00.0"));
        appointment.setLabel("tutorat de fermeture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);

        student = new Student();
        student.setName("Harry Potter");
        student.setCip("poth3285");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Gandalf the grey");
        student.setCip("greg6284");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Mickey Mouse");
        student.setCip("moum9836");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Mario Bros");
        student.setCip("brom7368");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Lucifer");
        student.setCip("luci3333");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        app.getTutos().add(group);

        // APP1 T4
        group = new TutoGroup();
        group.setTutoId("T4");

        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-05 14:30:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-05 16:00:00.0"));
        appointment.setLabel("tutorat d'ouverture");
        appointment.setAppointmentId(appointmentId++);
        group.getTutoAppointments().add(appointment);
        appointment = new TutoAppointment();
        appointment.setRoomId("C1-51xx");
        appointment.setStartTime(Timestamp.valueOf("2016-07-13 15:00:00.0"));
        appointment.setEndTime(Timestamp.valueOf("2016-07-13 16:30:00.0"));
        appointment.setLabel("tutorat de fermeture");
        group.getTutoAppointments().add(appointment);
        appointment.setAppointmentId(appointmentId++);

        student = new Student();
        student.setName("Jack Sparrow");
        student.setCip("spaj8389");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Bill Gates");
        student.setCip("gatb0374");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Ninja turtles");
        student.setCip("turn9263");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Pacman");
        student.setCip("pacm7362");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        student = new Student();
        student.setName("Le guitariste de Lamb of God");
        student.setCip("lamb3334");
        for (TutoAppointment tutoAppointment: group.getTutoAppointments()) {
            student.setPresenceEntry(tutoAppointment.getAppointmentId(),false);
        }
        student.setResp(Student.Responsability.NONE);
        group.getStudents().add(student);

        app.getTutos().add(group);

        res.getApps().add(app);

        app = new AppTutos();
        appID = new AppID();
        appID.setAppShortName("APP3");
        appID.setAppLongName("description of APP3");
        app.setAppID(appID);
        app.setValidGroups(false);

        teacher = new Teacher();
        teacher.setName("Some Random Teacher");
        teacher.setEmailAdd("some.random.teacher@usherbrooke.ca");
        app.getTeachers().add(teacher);

        res.getApps().add(app);

        return res;
    }

    @Override
    public GroupPageSaveResult savePresencesAndRespos(GroupPageSaveAction dataToSave) {
        GroupPageSaveResult res = new GroupPageSaveResult();
        res.setSaveSuccess(true);
        return res;
    }
}
