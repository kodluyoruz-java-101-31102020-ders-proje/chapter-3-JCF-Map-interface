package chapter3.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		
		basicHashMapAndTreeMap();
		complexHashMapAndTreeMap();
	}
	
	private static void basicHashMapAndTreeMap() {

		// HashMap Map'e eklenen değerleri sıralı bir şekilde tutmaz. Key değerinin hashcode'una bakıp mükerrer olup olmadığı kontrol ederek veriyi tutar.
		// Eğer aynı hashcode'a ait bir key ile eleman eklemeye çalışırsak eski değeri ezeriz.
		// put(key, value); fonksiyonuyla Map' anahtar değer vererek değer ekliyoruz.
		Map<Integer, List<Lesson>> studentLessonMapNative = new HashMap<Integer, List<Lesson>>();
		
		// TreeMap key alanındaki verilerin hashcode'una bakarak Map içinde sıralama yapar.
		// Map<Integer, List<Lesson>> studentLessonMapNative = new TreeMap<Integer, List<Lesson>>();
		
		studentLessonMapNative.put(2010, Arrays.asList(new Lesson("Fen", 80), new Lesson("İngilizce", 60) ));
		studentLessonMapNative.put(2005, Arrays.asList(new Lesson("Fen", 30), new Lesson("İngilizce", 30) ));
		studentLessonMapNative.put(2002, Arrays.asList(new Lesson("Matematik", 90), new Lesson("Türkçe", 70) ));
		studentLessonMapNative.put(2003, Arrays.asList(new Lesson("Fen", 80), new Lesson("İngilizce", 60) ));
	
		System.out.println("BEFORE MAP MODIFICATION!");
		System.out.println(studentLessonMapNative);
		
		// Map içindeki tüm anahtarları almak için .keySet() fonksiyonunu kullanırız.
		Set<Integer> keys = studentLessonMapNative.keySet();
		
		// Elde ettiğimiz tüm anahtar üzerinde döngü kurarak Map içindeki tüm verilere erişiriz.
		for(Integer studentNumber : keys) {
			
			// Map'ten key değerini vererek value olarak List<Lesson> tipinde ders litesini alıyoruz.
			List<Lesson> lessonList = studentLessonMapNative.get(studentNumber);
			for(int i=0; i < lessonList.size(); i++) {
				if(i == 0) {
				 	Lesson lesson = lessonList.get(i);
				 	lesson.setName("Matematik");
				 	lesson.setValue(0.0);
				}
				if(i == 1)
				{
					Lesson lesson = lessonList.get(i);
				 	lesson.setName("İngilizce");
				 	lesson.setValue(0.0);
				}
			}
			
		}
		
		// Map'teki verileri değiştirdikten sonra ders listelerinin değiştiğini görebiliriz.
		System.out.println("AFTER MAP MODIFICATION!");
		System.out.println(studentLessonMapNative);
		
		
		List<Lesson> allLessonList = new ArrayList<Lesson>();
		
		// Map içindeki tüm value değerlerini almak için .values() fonksiyonunu kullanabiliriz. 
		Collection< List<Lesson> > allLessons = studentLessonMapNative.values();
		
		for(List<Lesson> lessonList : allLessons) {
			allLessonList.addAll(lessonList);
		}
		
		System.out.println(allLessonList);
	}
	
	
	private static void complexHashMapAndTreeMap() {
		
		// Map için Key alanına Student sınıfından oluşturduğumuz nesneleri veriyoruz.
		// Key alanına verdiğimiz verilerin hashcode'una bakacaktır.
		Map<Student, List<Lesson>> studentLessonMap = new HashMap<Student, List<Lesson>>();
		
		studentLessonMap.put(
				
				new Student("A100", "Ahmet", "Kiraz"), 
				
				Arrays.asList(
						new Lesson("Matematik", 90),
						new Lesson("Türkçe", 70) ));
		
		
		studentLessonMap.put(
				
				new Student("F101", "Ali", "Yurt"), 
				
				Arrays.asList(
						new Lesson("Matematik", 90),
						new Lesson("Geometri", 70) ));
		
		
		studentLessonMap.put(
				new Student("B105", "Hale", "ÖzTürk"), 
				Arrays.asList(
						new Lesson("Biyoloji", 80),
						new Lesson("Türkçe", 60) ));
		
		studentLessonMap.put(
				
				new Student("D90", "Yudum", "Çiçek"),
				
				Arrays.asList(
						new Lesson("Kimya", 100),
						new Lesson("Fen", 60),
						new Lesson("Biyoloji", 50)));
		
		
		studentLessonMap.put(
				
				new Student("C90", "Yudum", "Çiçek"), 
				
				Arrays.asList(
						new Lesson("Kimya", 100)));
		
		
		showMap(studentLessonMap);

		
		// Map'teki verileri bir Comparator vasıtasıyla sıralamak istersek TreeMap kullanmalıyız.
		// Öğrenci ismine göre A-Z şeklinde sıralama yapan 'StudentComparatorByName' tipinde bir nesne oluşturup,
		// TreeMap kurucu metoduna gönderiyoruz.
		StudentComparatorByName studentComparatorByName = new StudentComparatorByName();
		SortedMap<Student, List<Lesson>> sortedStudentLessonMap = 
				new TreeMap<Student, List<Lesson>>(studentComparatorByName);
		
		sortedStudentLessonMap.putAll(studentLessonMap);
		
		
		
		System.out.println("Tree map sample");
		//System.out.println(sortedStudentLessonMap);
		showMap(sortedStudentLessonMap);
		

		// TreeMap A-Z şeklinde sıralı veri tutmayı sağlar.
		// TreeMap, SortedMap'den kalıtım almıştır.
		SortedMap<String, String> demo = new TreeMap<String, String>();
		demo.put("Pyhton", "A");
		demo.put("Java", "A");
		demo.put("C#", "A");
		demo.put("C++", "A");

		System.out.println(demo);
		
	}
	
	public static void showMap(Map<Student, List<Lesson>> studentLessonMap) {
		
		Set<Student> keys = studentLessonMap.keySet();
		
		for(Student key : keys) {
			
			System.out.println(key + " Ders Listesi");
			
		 	List<Lesson> lessonsOfStudent = studentLessonMap.get(key);
		 	
		 	for(Lesson lesson : lessonsOfStudent) {
		 		System.out.println(lesson);
		 	}
		 	System.out.println();
		}
	}

}
