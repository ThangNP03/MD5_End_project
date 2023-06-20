package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.Videos;
import ra.entity.user.Users;
import ra.repository.IVideoRepository;
import ra.service.IVideoService;

import java.util.List;
@Service
public class VideoService implements IVideoService {
    @Autowired
    private IVideoRepository videoRepository;

    @Override
    public List<Videos> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public Videos save(Videos video) {
        return videoRepository.save(video);

    }

    @Override
    public void deleteById(Long id) {
         videoRepository.deleteById(id);
      }

    @Override
    public Videos findById(Long id) {
        return videoRepository.findById(id).get();
    }

    @Override
    public boolean findByLike(Users users) {
        return videoRepository.findByLike(users);
    }

    @Override
    public List<Videos> searchVideosByTitle(String title) {
        return videoRepository.findByTitleContains(title);
    }
}
