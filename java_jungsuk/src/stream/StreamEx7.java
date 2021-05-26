package stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StudentEx7 {
	String name;
	boolean isMale;
	int hak;
	int ban;
	int score;
	
	StudentEx7(String name, boolean isMale, int hak, int ban, int score) {
		this.name = name;
		this.isMale = isMale;
		this.hak = hak;
		this.ban = ban;
		this.score = score;
	}
	
	String getName() { return name; }
	boolean isMale() { return isMale; }
	int getHak() { return hak; }
	int getBan() { return ban; }
	int getScore() { return score; }
	
	public String toString() {
		return String.format("[%s, %s, %d학년 %d반, %3d점]", name, isMale ? "남":"여", hak, ban, score);
	}
	
	//groupingBy()에서 사용
	enum Level { HIGH, MID ,LOW } // 성적을 상, 중, 하 세 단계로 분류
}

public class StreamEx7 {
	public static void main(String[] args) {
		StudentEx7[] stuArr = {
				new StudentEx7("나자바", true, 1, 1, 300),
				new StudentEx7("김지미", false, 1, 1, 250),
				new StudentEx7("김자바", true, 1, 1, 200),
				new StudentEx7("이지미", false, 1, 2, 150),
				new StudentEx7("남자바", true, 1, 2, 100),
				new StudentEx7("안지미", false, 1, 2, 50),
				new StudentEx7("황지미", false, 1, 3, 100),
				new StudentEx7("강지미", false, 1, 3, 150),
				new StudentEx7("이자바", true, 1, 3, 200),
				
				new StudentEx7("나자바", true, 2, 1, 300),
				new StudentEx7("김지미", false, 2, 1, 250),
				new StudentEx7("김자바", true, 2, 1, 200),
				new StudentEx7("이지미", false, 2, 2, 150),
				new StudentEx7("남자바", true, 2, 2, 100),
				new StudentEx7("안지미", false, 2, 2, 50),
				new StudentEx7("황지미", false, 2, 3, 100),
				new StudentEx7("강지미", false, 2, 3, 150),
				new StudentEx7("이자바", true, 2, 3, 200),
		};
		
		System.out.printf("1. 단순분할(성별로 분할)%n");
		Map<Boolean, List<StudentEx7>> stuBySex = Stream.of(stuArr)
									.collect(Collectors.partitioningBy(StudentEx7::isMale));
		
		List<StudentEx7> maleStudent = stuBySex.get(true);
		List<StudentEx7> femaleStudent = stuBySex.get(false);
		
		for(StudentEx7 s : maleStudent) System.out.println(s);
		for(StudentEx7 s : femaleStudent) System.out.println(s);
		
		System.out.printf("%n2. 단순분할 + 통계(성별 학생수)%n");
		Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
								.collect(Collectors.partitioningBy(StudentEx7::isMale, Collectors.counting()));
		
		System.out.println("남학생 수 : "+stuNumBySex.get(true));
		System.out.println("여학생 수 : "+stuNumBySex.get(false));
		
		System.out.printf("%n3. 단순분할 + 통계(성별 1등)%n");
		Map<Boolean, Optional<StudentEx7>> topScoreBySex = Stream.of(stuArr)
										.collect(Collectors.partitioningBy(StudentEx7::isMale,
												Collectors.maxBy(Comparator.comparingInt(StudentEx7::getScore))
												));
		System.out.println("남학생 1등 : "+topScoreBySex.get(true));
		System.out.println("여학생 1등 : "+topScoreBySex.get(false));
		
		Map<Boolean, StudentEx7> topScoreBySex2 = Stream.of(stuArr)
										.collect(Collectors.partitioningBy(StudentEx7::isMale,
												Collectors.collectingAndThen(
														Collectors.maxBy(Comparator.comparingInt(StudentEx7::getScore)), Optional::get
													)
												));
		System.out.println("남학생 1등 : "+ topScoreBySex2.get(true));
		System.out.println("여학생 1등 : "+ topScoreBySex2.get(false));
		
		System.out.printf("%n4. 다중분할(성별 불합격자, 100점 이하)%n");
		
		Map<Boolean, Map<Boolean, List<StudentEx7>>> failedStuBySex =
				Stream.of(stuArr).collect(partitioningBy(StudentEx7::isMale, partitioningBy(s -> s.getScore() <= 100)
						));
		
		List<StudentEx7> failedMailStu = failedStuBySex.get(true).get(true);
		List<StudentEx7> failedFemailStu = failedStuBySex.get(false).get(true);
		
		for(StudentEx7 s : failedMailStu) System.out.println(s);
		for(StudentEx7 s : failedFemailStu) System.out.println(s);
		
				
	}
}
