package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

	private Long memberId;
	public String username;
	private int age;
	private Long teamId;
	public String teamName;

	@QueryProjection
	public MemberTeamDto(Long memberId, String username, int age, Long teamId, String teamName) {
		this.memberId = memberId;
		this.username = username;
		this.age = age;
		this.teamId = teamId;
		this.teamName = teamName;
	}
}
