//package mvcexample;
//
//import javafx.scene.control.Label;
//import util.Observable;
//import util.Observer;
//
//public class VParity extends Label implements Observer {
//
//	@Override
//	public void update(Observable o) {
//		MCounter mcounter = (MCounter)o;
//		if(mcounter.getCount()%2==0) {
//			this.setText("EVEN");
//		} else {
//			this.setText("ODD");
//		}
//	}
//
//}
