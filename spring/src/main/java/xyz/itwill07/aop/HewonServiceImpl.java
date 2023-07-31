package xyz.itwill07.aop;

import java.util.List;

import lombok.Setter;

// 핵심 관심 모듈
public class HewonServiceImpl implements HewonService {
	// 필드에 @Setter 어노테이션을 사용하면 필드에 대한 Setter 메소드 생성
	@Setter
	private HewonDAO hewonDAO;

	@Override
	public void addHewon(Hewon hewon) {
		System.out.println("※※※ HewonServiceImpl 클래스의 addHewon(Hewon hewon) 메소드 호출 ※※※");
		hewonDAO.insertHewon(hewon);
	}

	@Override
	public Hewon getHewon(int num) {
		System.out.println("※※※ HewonServiceImpl 클래스의 getHewon(int num) 메소드 호출 ※※※");
		return hewonDAO.selectHewon(num);
	}

	@Override
	public List<Hewon> getHewonList() {
		System.out.println("※※※ HewonServiceImpl 클래스의 getHewonList() 메소드 호출 ※※※");
		return hewonDAO.selectHewonList();
	}
}
