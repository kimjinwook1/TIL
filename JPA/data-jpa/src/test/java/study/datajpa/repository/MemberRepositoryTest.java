package study.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	TeamRepository teamRepository;
	@PersistenceContext
	EntityManager em;

	@Test
	void testMember() {

		System.out.println("memberRepository = " + memberRepository.getClass());
		//given
		Member member = new Member("memberA");
		Member savedMember = memberRepository.save(member);

		//when
		Member findMember = memberRepository.findById(savedMember.getId()).get();

		//then
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	void basicCRUD() {
		//given
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberRepository.save(member1);
		memberRepository.save(member2);
		//when
		Member findMember1 = memberRepository.findById(member1.getId()).get();
		Member findMember2 = memberRepository.findById(member2.getId()).get();
		//then
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);

		findMember1.setUsername("member!!!!");

		//리스트 조회 검증
		List<Member> all = memberRepository.findAll();
		assertThat(all.size()).isEqualTo(2);

		//카운트 검증
		long count = memberRepository.count();
		assertThat(count).isEqualTo(2);

		//삭제 검증
		memberRepository.delete(member1);
		memberRepository.delete(member2);
		long deletedCount = memberRepository.count();
		assertThat(deletedCount).isEqualTo(0);
	}

	@Test
	void findByUsernameAndAgeGreaterThan() {
		//given
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("AAA", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		//when
		List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

		//then
		assertThat(result.get(0).getUsername()).isEqualTo("AAA");
		assertThat(result.get(0).getAge()).isEqualTo(20);
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	void findHelloBy() {
		List<Member> helloBy = memberRepository.findTop3HelloBy();
	}

	@Test
	void testNameQuery() {
		//given
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		//when
		List<Member> result = memberRepository.findByUsername("AAA");
		Member findMember = result.get(0);

		//then
		assertThat(findMember).isEqualTo(m1);
	}

	@Test
	void testQuery() {
		//given
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		//when
		List<Member> result = memberRepository.findUser("AAA", 10);

		//then
		assertThat(result.get(0)).isEqualTo(m1);
	}

	@Test
	void findMemberDto() {
		//given
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		//when
		List<MemberDto> memberDto = memberRepository.findMemberDto();

		//then
		for (MemberDto dto : memberDto) {
			System.out.println("dto = " + dto);
		}
	}

	@Test
	void findUsernameList() {
		//given
		Team team = new Team("teamA");
		teamRepository.save(team);

		Member m1 = new Member("AAA", 10);
		m1.setTeam(team);
		memberRepository.save(m1);

		//when
		List<String> usernameList = memberRepository.findUsernameList();

		//then
		for (String s : usernameList) {
			System.out.println("s = " + s);
		}
	}

	@Test
	void findByNames() {
		//given
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		//when
		List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));

		//then
		for (Member member : result) {
			System.out.println("member = " + member);
		}
	}

	@Test
	void returnType() {
		//given
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		//when
		List<Member> result = memberRepository.findListByUsername(
			"CCC"); //리스트 조회는 무조건 Null이 아님 -> 없으면 empty컬렉션이 반환됨
		System.out.println("result.size() = " + result.size());

		Member findMember = memberRepository.findMemberByUsername("CCC"); //단건 조회는 없으면 Null 반환
		System.out.println("findMember = " + findMember);

		Optional<Member> findOptionalMember = memberRepository.findOptionalByUsername("CCC");
		//Optional 조회 -> 없으면 Optional.empty 반환
		System.out.println("findOptionalMember = " + findOptionalMember);
		//then
	}

	@Test
	void paging() {
		//given
		memberRepository.save((new Member("member1", 10)));
		memberRepository.save((new Member("member2", 10)));
		memberRepository.save((new Member("member3", 10)));
		memberRepository.save((new Member("member4", 10)));
		memberRepository.save((new Member("member5", 10)));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		//when
		Page<Member> page = memberRepository.findByAge(age, pageRequest);

		Page<MemberDto> map = page.map(
			m -> new MemberDto(m.getId(), m.getUsername(), null)); //map을 통해 객체를 쉽게 변경할 수 있다.
		List<Member> content = page.getContent();

		//then
		assertThat(content.size()).isEqualTo(3);
		assertThat(page.getTotalElements()).isEqualTo(5);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.isFirst()).isTrue();
		assertThat(page.hasNext()).isTrue();
	}


	@Test
	void bulkUpdate() {
		//given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 19));
		memberRepository.save(new Member("member3", 20));
		memberRepository.save(new Member("member4", 21));
		memberRepository.save(new Member("member5", 40));

		//when
		int resultCount = memberRepository.bulkAgePlus(20);
//		em.clear();

		List<Member> result = memberRepository.findByUsername("member5");
		Member member5 = result.get(0);
		System.out.println("member5 = " + member5);

//		then
		assertThat(resultCount).isEqualTo(3);
	}

	@Test
	void findMemberLazy() {
		//given
		//member1 -> teamA
		//member2 -> teamB

		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		teamRepository.save(teamA);
		teamRepository.save(teamB);
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 10, teamB);
		memberRepository.save(member1);
		memberRepository.save(member2);

		em.flush();
		em.clear();

		//when
		List<Member> members = memberRepository.findEntityGraphByUsername("member1");

		for (Member member : members) {
			System.out.println("member = " + member.getUsername());
			System.out.println("member.teamClass = " + member.getTeam().getClass());
			System.out.println("member.team = " + member.getTeam().getName());
		}
		//then
	}

	@Test
	void queryHint() {
		//given
		Member member1 = new Member("member1", 10);
		memberRepository.save(member1);
		em.flush();
		em.clear();

		//when
		Member findMember = memberRepository.findReadOnlyByUsername("member1");
		findMember.setUsername("member2");

		em.flush();
		//then
	}

	@Test
	void lock() {
		//given
		Member member1 = new Member("member1", 10);
		memberRepository.save(member1);
		em.flush();
		em.clear();

		//when
		List<Member> result = memberRepository.findLockByUsername("member1");
	}

	@Test
	void callCustom() {
		//given
		List<Member> result = memberRepository.findMemberCustom();
	}

	@Test
	void queryByExample() {
		//given
		Team teamA = new Team("teamA");
		em.persist(teamA);

		Member m1 = new Member("m1", 0, teamA);
		Member m2 = new Member("m2", 0, teamA);
		em.persist(m1);
		em.persist(m2);

		em.flush();
		em.clear();

		//when
		//Probe 생성
		Member member = new Member("m1");
		Team team = new Team("teamA");

		member.setTeam(team);
		ExampleMatcher matcher = ExampleMatcher.matching()
			.withIgnorePaths("age");

		Example<Member> example = Example.of(member, matcher);

		List<Member> result = memberRepository.findAll(example);

		//then
		assertThat(result.get(0).getUsername()).isEqualTo("m1");
	}

	@Test
	void projections() {
		//given
		Team teamA = new Team("teamA");
		em.persist(teamA);

		Member m1 = new Member("m1", 0, teamA);
		Member m2 = new Member("m2", 0, teamA);
		em.persist(m1);
		em.persist(m2);

		em.flush();
		em.clear();

		//when
		List<NestedClosedProjections> result = memberRepository.findProjectionsByUsername("m1",NestedClosedProjections.class);

		for (NestedClosedProjections nestedClosedProjections : result) {
			String username = nestedClosedProjections.getUsername();
			System.out.println("username = " + username);
			String teamName = nestedClosedProjections.getTeam().getName();
			System.out.println("teamName = " + teamName);
		}
	}

	@Test
	void nativeQuery() {
		//given
		Team teamA = new Team("teamA");
		em.persist(teamA);

		Member m1 = new Member("m1", 0, teamA);
		Member m2 = new Member("m2", 0, teamA);
		em.persist(m1);
		em.persist(m2);

		em.flush();
		em.clear();

	    //when
		Page<MemberProjection> result = memberRepository.findByNativeProjection(
			PageRequest.of(0, 10));
		List<MemberProjection> content = result.getContent();
		for (MemberProjection memberProjection : content) {
			System.out.println("memberProjection.getUsername() = " + memberProjection.getUsername());
			System.out.println("memberProjection.getTeamName() = " + memberProjection.getTeamName());
		}
		//then
	}
}

