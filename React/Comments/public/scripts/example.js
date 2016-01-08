
// Comment Box class
var CommentBox = React.createClass({
  render: function() { // Only function in the 'CommentBox' class
    return (
      <div className="commentBox">
        <h1>Comments</h1>
        <CommentList />   // The JSX compiler will automatically rewrite HTML tags 
        <CommentForm />   // to React.createElement(tagName) expressions and leave everything else alone.
      </div>              // This is to prevent the pollution of the global namespace.
    );
  }
});

// Comment list Class
var CommentList = React.createClass({
  render: function() {
    return (
      <div className="commentList">
        <Comment author="Pete Hunt">This is one comment</Comment>
        <Comment author="Jordan Walke">This is *another* comment</Comment>
      </div>
    );
  }
});

// Comment Form Class
var CommentForm = React.createClass({
  render: function() {
    return (
      <div className="commentForm">
        Hello, world! I am a CommentForm.
      </div>
    );
  }
});

// Comment Class
var Comment = React.createClass({
  render: function() {
    return (
      <div className="comment">
        <h2 className="commentAuthor"> // Data passed in from a parent component is available as a 'property' on 
          {this.props.author}          // the child component. These 'properties' are accessed through this.props.
        </h2>                          // Using props, we will be able to read the data passed to the Comment from the CommentList
        {this.props.children}
      </div>
    );
  }
});


// Instantiates the root component, starts the framework, 
// and injects the markup into a raw DOM element, provided as the second argument.
//
// It is important that ReactDOM.render remain at the bottom of the script for
// this tutorial. ReactDOM.render should only be called after the composite components
// have been defined.
ReactDOM.render(
  <CommentBox />,
  document.getElementById('content')
);