package ra.service.serviceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.entity.Comment;
import ra.repository.ICommmentRepository;
import ra.service.ICommentService;

import java.util.List;
@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommmentRepository commmentRepository;
    @Override
    public List<Comment> findAll() {
        return commmentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
     return    commmentRepository.save(comment);

    }

    @Override
    public void deleteById(Long id) {
        commmentRepository.deleteById(id);
;
    }

    @Override
    public Comment findById(Long id) {
        return commmentRepository.findById(id).get();
    }
}
