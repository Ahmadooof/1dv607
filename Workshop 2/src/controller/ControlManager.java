//package controller;
//
//import model.IPersistence;
//import model.MemberRegistry;
//import view.IView;
//
//import java.io.IOException;
//
//public class ControlManager {
//    private IView view;
//    private IPersistence persistence;
//    private MemberRegistry memberRegistry;
//
//
//    public ControlManager(IView view, IPersistence persistence) {
//        this.view = view;
//        this.persistence = persistence;
//        memberRegistry = new MemberRegistry(persistence);
//    }
//
//    public void t() {
//        memberRegistry.loadMembers();
//    }
//
//    public void userChoice() throws IOException {
//        MemberControl memberControl = new MemberControl();
//        BoatControl boatControl = new BoatControl();
//        switch (view.userInput()) {
//
//        }
//    }
//}
