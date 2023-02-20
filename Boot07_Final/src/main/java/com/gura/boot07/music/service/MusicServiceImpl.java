package com.gura.boot07.music.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.music.dao.MusicDao;
import com.gura.boot07.music.dto.MusicDto;

@Service
public class MusicServiceImpl implements MusicService{
	
	@Autowired MusicDao dao;
	
	@Override
	public void saveFile(MultipartFile file, HttpServletRequest request) {
		//업로드된 음악 파일의 정보를 담을 dto 객체 생성
		MusicDto dto=new MusicDto();
		
		//업로드한 클라이언트의 아이디
		String id=(String)request.getSession().getAttribute("id");
		
		dto.setWriter(id);
		
		//원본 파일명 -> 저장할 파일 이름 만들기위해서 사용됨
		String orgFileName = file.getOriginalFilename();
		dto.setOrgFileName(orgFileName);
		
		//파일 크기 -> 다운로드가 없으므로, 여기서는 필요 없다.
		long fileSize = file.getSize();
		
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
		String realPath = request.getServletContext().getRealPath("/resources/upload");
		//db 에 저장할 저장할 파일의 상세 경로
		String filePath = realPath + File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		if(!upload.exists()) {
			//만약 디렉토리가 존재하지X
			upload.mkdir();//폴더 생성
		}
		//저장할 파일의 이름을 구성한다. -> 우리가 직접 구성해줘야한다.
		String saveFileName = System.currentTimeMillis() + orgFileName;
		
		dto.setSaveFileName(saveFileName);
		
		try {
			File mp3File=new File(filePath + saveFileName);
			//upload 폴더에 파일을 저장한다.
			file.transferTo(mp3File);
			//mp3 파일에서 meta data 추출하기 
			MP3File mp3=(MP3File)AudioFileIO.read(mp3File);
			//AbstractID3Tag aTag=mp3.getID3v2Tag();
			Tag tag=mp3.getTag();
			//제목
			String title=tag.getFirst(FieldKey.TITLE);
			
			//만일 제목 정보가 없으면
			if(title==null) {
				//원본 파일명을 제목으로 설정
				title=orgFileName;
			}
			dto.setTitle(title);
			//musicion 
			String artist = tag.getFirst(FieldKey.ARTIST);
			//만일 아티스트 정보가 없으면
			if(artist == null) {
				artist="정보없음";
			}
			dto.setArtist(artist);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//DB 에 저장 
		dao.insert(dto);
		
	}

	@Override
	public void getList(ModelAndView mView, HttpSession session) {
		//로그인된 아이디
		String id=(String)session.getAttribute("id");
		//아이디를 이용해서 로그인된 클라이언트가 업로드한 음악 파일 목록만 얻어낸다.
		List<MusicDto> list=dao.getList(id);
		//ModelAndView 객체에 담기
		mView.addObject("list", list);
	}

	@Override
	public MusicDto getDetail(int num) {
		
		return dao.getData(num);
	}

	@Override
	public void deleteFile(int num, HttpServletRequest request) {
		//삭제할 파일의 정보를 읽어온다.
		MusicDto dto=dao.getData(num);
		//1. 파일 시스템에서 삭제 (삭제할 파일을 가리키는 File 객체가 필요하다)
		
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
		String realPath = request.getServletContext().getRealPath("/resources/upload");
		//db 에 저장할 삭제할 파일의 상세 경로
		String filePath = realPath + File.separator + dto.getSaveFileName();
		//파일객체를 생성해서 
		File f=new File(filePath);
		//메소드를 이용해서 삭제한다.
		f.delete();
		//2. DB 에서도 삭제 
		dao.delete(num);
	}

}












