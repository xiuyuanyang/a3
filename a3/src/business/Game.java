package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Mi;
import beans.Qiu;

public class Game {
	private Map<String, Qiu> qiu_group = new HashMap<String, Qiu>() ;
	private List<Mi> mi_group = new ArrayList<Mi>() ;
	private double initR = 20;
	private double initSpeed = 8;

	public Map<String, Qiu> getQiu_group() {
		return qiu_group;
	}

	public void setQiu_group(Map<String, Qiu> qiu_group) {
		this.qiu_group = qiu_group;
	}

	public List<Mi> getMi_group() {
		return mi_group;
	}

	public void setMi_group(List<Mi> mi_group) {
		this.mi_group = mi_group;
	}

	public void init() {
		if (qiu_group == null) {
			qiu_group = new HashMap<String, Qiu>();
		}

		if (mi_group == null) {
			mi_group = new ArrayList<Mi>();
		}
	}

	public int addQiu(Qiu q, String id) {
		// String id = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			qiu_group.put(id, q);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	public void deleteQiu(String id) {
		qiu_group.remove(id);
	}

	public void addMi(Mi m) {
		mi_group.add(m);
	}

	public void deleteMi(Mi m) {
		mi_group.remove(m);
	}

	public Qiu setQiu(String id, Qiu q) {
		double r, speed;
		for (Mi m : mi_group) {
			if (ifEatMi(q, m)) {
				if (mi_group.remove(m)) {
					r = (q.getR() + m.getR());
					q.setR(r);
					speed = initSpeed / r;
					q.setSpeed(speed);
					
				}
			}
			
		}
		qiu_group.replace(id, q);
		return qiu_group.get(id);
	}

	private boolean ifEatMi(Qiu q, Mi mi) {
		if ((mi.getX() - mi.getR()) > (q.getX() - q.getR()) && (mi.getX() + mi.getR()) < (q.getX() + q.getR())
				&& (mi.getY() - mi.getR()) > (q.getY() - q.getR()) && (mi.getY() + mi.getR()) < (q.getY() + q.getR())) {
			return true;
		} else {
			return false;
		}
	}

}
